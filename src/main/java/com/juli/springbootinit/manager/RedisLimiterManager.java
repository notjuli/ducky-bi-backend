package com.juli.springbootinit.manager;

import com.juli.springbootinit.common.ErrorCode;
import com.juli.springbootinit.exception.BusinessException;
import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 提供RedisLimiter限流基础服务
 *
 * @author julii
 */
@Service
public class RedisLimiterManager {
    @Resource
    RedissonClient redissonClient = Redisson.create();

    /**
     * 限流操作
     *
     */
    public void doRateLimit(String key){
        // 创建RateLimiter，key是限流器的名称
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);

        // 初始化RateLimiter，设置每秒钟产生的令牌数。OVERALL统计所有客户端的请求总和
        // 参数：限流器类型，令牌发放速率、rate time interval、rate time interval unit
        rateLimiter.trySetRate(RateType.OVERALL, 2, 1, RateIntervalUnit.SECONDS);

        // 每当一个操作来了之后，请求令牌，permits – the number of permits to acquire
        boolean canOP = rateLimiter.tryAcquire(1);
        if(!canOP){
            throw new BusinessException(ErrorCode.TOO_MANY_REQUEST);
        }
    }
}
