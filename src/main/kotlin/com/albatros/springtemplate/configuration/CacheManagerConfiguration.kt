package com.albatros.springtemplate.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.connection.RedisConnectionFactory
import java.time.Duration
import java.util.*

@Configuration
class CacheManagerConfiguration {
    @Bean
    fun cacheManager(connectionFactory: RedisConnectionFactory) =
        RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(connectionFactory))
            .cacheDefaults(
                RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(
                        Duration.ofSeconds(4000)
                    )
                    .disableCachingNullValues()
            )
            .transactionAware()
            .withInitialCacheConfigurations(
                Collections.singletonMap(
                    "predefined",
                    RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
                )
            ).build()
}