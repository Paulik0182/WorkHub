package com.nayya.workhub.domain.entity.pracuj_pl_for_filter

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RuntimeConfig(

    @SerializedName("ADS_ENABLED")
    val ADS_ENABLED: Boolean?,

    @SerializedName("API_CLIENT_GATEWAY")
    val API_CLIENT_GATEWAY: String?,

    @SerializedName("API_INTERNAL_TRANSLATE")
    val API_INTERNAL_TRANSLATE: String?,

    @SerializedName("APP_NAME")
    val APP_NAME: String?,

    @SerializedName("APP_VERSION")
    val APP_VERSION: String?,

    @SerializedName("AUTH_PAGE_GATEWAY_ADDRESS")
    val AUTH_PAGE_GATEWAY_ADDRESS: String?,

    @SerializedName("CDN_PATH")
    val CDN_PATH: String?,

    @SerializedName("CDN_ROOT_PATH")
    val CDN_ROOT_PATH: String?,

    @SerializedName("ENVIRONMENT")
    val ENVIRONMENT: String?,

    @SerializedName("GOOGLE_CLIENT_ID")
    val GOOGLE_CLIENT_ID: String?,

    @SerializedName("GOOGLE_STATE_COOKIE_DOMAIN")
    val GOOGLE_STATE_COOKIE_DOMAIN: String?,

    @SerializedName("GTM_CONTAINER_ID")
    val GTM_CONTAINER_ID: String?,

    @SerializedName("GTM_ENABLED")
    val GTM_ENABLED: Boolean?,

    @SerializedName("SENTRY_DSN")
    val SENTRY_DSN: String?,

    @SerializedName("SENTRY_ENABLED")
    val SENTRY_ENABLED: Boolean?,

    @SerializedName("SENTRY_RELEASE_NAME")
    val SENTRY_RELEASE_NAME: String?,

    @SerializedName("SENTRY_RELEASE_VERSION")
    val SENTRY_RELEASE_VERSION: String?,

    @SerializedName("SUPPORTED_BROWSERS")
    val SUPPORTED_BROWSERS: String?,

    @SerializedName("TRACKER_COLLECTOR_ENDPOINT")
    val TRACKER_COLLECTOR_ENDPOINT: String?,

    @SerializedName("TRACKER_COOKIE_DOMAIN")
    val TRACKER_COOKIE_DOMAIN: String?,

    @SerializedName("TRACKER_CORE_URL")
    val TRACKER_CORE_URL: String?,

    @SerializedName("TRACKER_ENABLED")
    val TRACKER_ENABLED: Boolean?

) : Parcelable