package com.lmaye.cloud.example.delayqueue.retrying;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import com.lmaye.cloud.core.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * -- Kafka Retry Listener
 *
 * @author Lmay Zhou
 * @date 2021/12/16 14:04
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Slf4j
public class KafkaRetryListener implements RetryListener {
    @Override
    public <SendResult> void onRetry(Attempt<SendResult> attempt) {
        if (attempt.hasException()) {
            log.error("[Retry] exception: ", attempt.getExceptionCause());
        } else if (attempt.hasResult()) {
            if (Objects.isNull(attempt.getResult())) {
                log.error("[Retry] return data is null");
            } else {
                log.error("[Retry] return data is: {}", GsonUtils.toJson(attempt.getResult()));
            }
        }
        if(Objects.equals(3L, attempt.getAttemptNumber())) {
            // TODO 重试多次处理
            log.error("-------------------> Send Massage <-------------------");
        }
    }
}
