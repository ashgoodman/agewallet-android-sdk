package io.agewallet.sdk

import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom

/**
 * Security utilities for PKCE and state generation.
 */
internal object Security {
    private val secureRandom = SecureRandom()

    /**
     * Generate a cryptographically secure PKCE verifier.
     * Returns a base64-URL encoded string of 64 random bytes.
     */
    fun generateVerifier(): String {
        val bytes = ByteArray(64)
        secureRandom.nextBytes(bytes)
        return base64UrlEncode(bytes)
    }

    /**
     * Generate a PKCE challenge from a verifier using S256 method.
     * Returns SHA256(verifier) as base64-URL encoded string.
     */
    fun generateChallenge(verifier: String): String {
        val bytes = verifier.toByteArray(Charsets.UTF_8)
        val digest = MessageDigest.getInstance("SHA-256").digest(bytes)
        return base64UrlEncode(digest)
    }

    /**
     * Generate a random state parameter for CSRF protection.
     * Returns a 32-character hex string.
     */
    fun generateState(): String {
        return generateRandomHex(16)
    }

    /**
     * Generate a random nonce for replay protection.
     * Returns a 32-character hex string.
     */
    fun generateNonce(): String {
        return generateRandomHex(16)
    }

    private fun generateRandomHex(byteLength: Int): String {
        val bytes = ByteArray(byteLength)
        secureRandom.nextBytes(bytes)
        return bytes.joinToString("") { "%02x".format(it) }
    }

    private fun base64UrlEncode(bytes: ByteArray): String {
        return Base64.encodeToString(bytes, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
    }
}
