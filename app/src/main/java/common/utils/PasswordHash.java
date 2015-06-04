package common.utils;
/* 
 * Password Hashing With PBKDF2 (http://crackstation.net/hashing-security.htm).
 * Copyright (c) 2013, Taylor Hornby
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, 
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;

import android.text.TextUtils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/*
 * PBKDF2 salted password hashing.
 * Author: havoc AT defuse.ca
 * www: http://crackstation.net/hashing-security.htm
 */
public class PasswordHash {
	
	// 客户端用，固定盐
	private static final byte[] SALT = {1,'k','z', 2, 3, 3, 8, 7,
										0, 7, 6, 'a', 5, 'j', 2, 4,
										'y', 's', 'i', 6, '3', 9, 'e', 'h'};

	private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

	// The following constants may be changed without breaking existing hashes.
	//private static final int SALT_BYTE_SIZE = 24;
	private static final int HASH_BYTE_SIZE = 24;
	private static final int PBKDF2_ITERATIONS = 1000;

	/**
	 * Returns a salted PBKDF2 hash of the password.
	 *
	 * @param   password    the password to hash
	 * @return              a salted PBKDF2 hash of the password
	 */
	public static String createHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		if(TextUtils.isEmpty(password)) {
			return "";
		} else {
			return createHash(password.toCharArray());
		}
	}

	/**
	 * Returns a salted PBKDF2 hash of the password.
	 *
	 * @param   password    the password to hash
	 * @return              a salted PBKDF2 hash of the password
	 */
	public static String createHash(char[] password)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		// Generate a random salt
		//SecureRandom random = new SecureRandom();
		//byte[] salt = new byte[SALT_BYTE_SIZE];
		//random.nextBytes(salt);
		byte[] salt = SALT;

		// Hash the password
		byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
		// format iterations:salt:hash
	//	return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" +  toHex(hash);
		return toHex(hash);
	}


	/**
	 *  Computes the PBKDF2 hash of a password.
	 *
	 * @param   password    the password to hash.
	 * @param   salt        the salt
	 * @param   iterations  the iteration count (slowness factor)
	 * @param   bytes       the length of the hash to compute in bytes
	 * @return              the PBDKF2 hash of the password
	 */
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return skf.generateSecret(spec).getEncoded();
	}


	/**
	 * Converts a byte array into a hexadecimal string.
	 *
	 * @param   array       the byte array to convert
	 * @return              a length*2 character string encoding the byte array
	 */
	private static String toHex(byte[] array)
	{
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if(paddingLength > 0) 
			return String.format("%0" + paddingLength + "d", 0) + hex;
		else
			return hex;
	}

}
