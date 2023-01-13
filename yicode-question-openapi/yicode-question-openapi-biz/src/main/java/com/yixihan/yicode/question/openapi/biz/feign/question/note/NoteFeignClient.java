package com.yixihan.yicode.question.openapi.biz.feign.question.note;

import com.yixihan.yicode.question.api.rest.note.NoteApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 评论 FeignClient
 *
 * @author yixihan
 * @date 2023/1/13 12:38
 */
@FeignClient(value = "${feign.client.yicode-question.name}")
public interface NoteFeignClient extends NoteApi {
}
