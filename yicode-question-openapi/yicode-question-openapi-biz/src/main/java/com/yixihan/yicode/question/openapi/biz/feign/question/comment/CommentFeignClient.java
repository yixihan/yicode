package com.yixihan.yicode.question.openapi.biz.feign.question.comment;

import com.yixihan.yicode.question.api.rest.comment.CommentApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 评论 FeignClient
 *
 * @author yixihan
 * @date 2023/1/13 12:38
 */
@FeignClient(value = "${feign.client.yicode-question.name}")
public interface CommentFeignClient extends CommentApi {
}
