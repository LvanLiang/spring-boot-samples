package com.liang.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分布式id主键生成策略
 */
public final class IdUtil {
	private IdUtil() {
	}

	private static final Pattern PATTERN_LONG_ID = Pattern.compile("^([0-9]{15})([0-9a-f]{32})([0-9a-f]{3})$");

	private static final Pattern PATTERN_HOSTNAME = Pattern.compile("^.*\\D+([0-9]+)$");

	private static final long OFFSET = LocalDate.of(2000, 1, 1).atStartOfDay(ZoneId.of("Z")).toEpochSecond();

	private static final long MAX_NEXT = 0b11111_11111111_111L;

	private static final long SHARD_ID = getServerIdAsLong();

	private static long offset = 0;

	private static long lastEpoch = 0;

	public static long getId() {
		return nextId(System.currentTimeMillis() / 1000);
	}

	private static synchronized long nextId(long epochSecond) {
		if (epochSecond < lastEpoch) {
			epochSecond = lastEpoch;
		}
		if (lastEpoch != epochSecond) {
			lastEpoch = epochSecond;
			reset();
		}
		offset++;
		long next = offset & MAX_NEXT;
		if (next == 0) {
			return nextId(epochSecond + 1);
		}
		return generateId(epochSecond, next, SHARD_ID);
	}

	private static void reset() {
		offset = 0;
	}

	private static long generateId(long epochSecond, long next, long shardId) {
		return ((epochSecond - OFFSET) << 21) | (next << 5) | shardId;
	}

	private static long getServerIdAsLong() {
		try {
			String hostname = InetAddress.getLocalHost().getHostName();
			Matcher matcher = PATTERN_HOSTNAME.matcher(hostname);
			if (matcher.matches()) {
				long n = Long.parseLong(matcher.group(1));
				if (n >= 0 && n < 8) {
					return n;
				}
			}
		} catch (UnknownHostException e) {
		}
		return 0;
	}

	public static long stringIdToLongId(String stringId) {
		// a stringId id is composed as timestamp (15) + uuid (32) + serverId (000~fff).
		Matcher matcher = PATTERN_LONG_ID.matcher(stringId);
		if (matcher.matches()) {
			long epoch = Long.parseLong(matcher.group(1)) / 1000;
			String uuid = matcher.group(2);
			byte[] sha1 = sha1AsBytes(uuid);
			long next = ((sha1[0] << 24) | (sha1[1] << 16) | (sha1[2] << 8) | sha1[3]) & MAX_NEXT;
			long serverId = Long.parseLong(matcher.group(3), 16);
			return generateId(epoch, next, serverId);
		}
		throw new IllegalArgumentException("Invalid id: " + stringId);
	}
	private static byte[] sha1AsBytes(String input) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		md.update(input.getBytes(StandardCharsets.UTF_8));
		return md.digest();
	}

}
