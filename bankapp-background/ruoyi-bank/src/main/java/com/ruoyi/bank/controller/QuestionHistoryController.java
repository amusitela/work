package com.ruoyi.bank.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bank.domain.QuestionHistory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bank.service.IQuestionHistoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 历史记录Controller
 * 
 * @author ljn
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/test/history")
public class QuestionHistoryController extends BaseController
{
    @Autowired
    private IQuestionHistoryService questionHistoryService;

    /**
     * 查询历史记录列表
     */
//    @PreAuthorize("@ss.hasPermi('test:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(QuestionHistory questionHistory)
    {

        List<QuestionHistory> list = questionHistoryService.selectQuestionHistoryList(questionHistory);
        System.out.println(list);
        return getDataTable(list);
    }
    @GetMapping("/read")
    public TableDataInfo asRead(QuestionHistory questionHistory)
    {
        questionHistoryService.markAllAsRead();
        List<QuestionHistory> list = questionHistoryService.selectQuestionHistoryList(questionHistory);
        return getDataTable(list);
    }

    @PostMapping("/markAsRead")
    public ResponseEntity<String> markMessagesAsRead(@RequestBody List<Long> idList) {
        try {
            questionHistoryService.markAsReadBatch(idList);
            return ResponseEntity.ok("消息已标记为已读");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("批量标记消息为已读失败");
        }
    }

    /**
     * 导出历史记录列表
     */
//    @PreAuthorize("@ss.hasPermi('test:history:export')")
    @Log(title = "历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QuestionHistory questionHistory)
    {
        List<QuestionHistory> list = questionHistoryService.selectQuestionHistoryList(questionHistory);
        ExcelUtil<QuestionHistory> util = new ExcelUtil<QuestionHistory>(QuestionHistory.class);
        util.exportExcel(response, list, "历史记录数据");
    }

    /**
     * 获取历史记录详细信息
     */
//    @PreAuthorize("@ss.hasPermi('test:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(questionHistoryService.selectQuestionHistoryById(id));
    }

    /**
     * 新增历史记录
     */
//    @PreAuthorize("@ss.hasPermi('test:history:add')")
    @Log(title = "历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QuestionHistory questionHistory)
    {
        return toAjax(questionHistoryService.insertQuestionHistory(questionHistory));
    }

    /**
     * 修改历史记录
     */
    @Log(title = "历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit()
    {
        questionHistoryService.markAllAsRead();
        return toAjax(true);
    }

    /**
     * 删除历史记录
     */
    @PreAuthorize("@ss.hasPermi('test:history:remove')")
    @Log(title = "历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(questionHistoryService.deleteQuestionHistoryByIds(ids));
    }
}
