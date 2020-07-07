package com.mydubbo.dubbo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 针对审查要点给出的审查意见
 * @author yinxp@dist.com.cn
 * @date 2019/2/15
 */
@Entity
@Table(name = "ars_review_opinion")
@SequenceGenerator(name="ID_SEQ",sequenceName = "SEQ_ARS_HIBERNATE", allocationSize = 1)
public class reviewopinion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ID_SEQ")
    private Long id;

    // 问题位置
    @Column(length = 4000)
    private String questionPosition;

    // 问题描述
    @Column(length = 4000)
    private String questionDesc;

    // 所属的审查要点  @see ReviewPoint.id
    private Long reviewPointId;

    // 所属的审查任务id  @see ReviewTask.id
    private Long reviewTaskId;

    private Date createTime;

    // 重要标记
    @org.hibernate.annotations.Type(type="yes_no")
    private Boolean importantTag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getReviewTaskId() {
        return reviewTaskId;
    }

    public void setReviewTaskId(Long reviewTaskId) {
        this.reviewTaskId = reviewTaskId;
    }

    public String getQuestionPosition() {
        return questionPosition;
    }

    public void setQuestionPosition(String questionPosition) {
        this.questionPosition = questionPosition;
    }

    public Boolean getImportantTag() {
        return importantTag;
    }

    public void setImportantTag(Boolean importantTag) {
        this.importantTag = importantTag;
    }

    public Long getReviewPointId() {
        return reviewPointId;
    }

    public void setReviewPointId(Long reviewPointId) {
        this.reviewPointId = reviewPointId;
    }
}
