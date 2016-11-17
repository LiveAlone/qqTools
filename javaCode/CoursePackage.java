import java.io.Serializable;
import java.util.Date;

/**
 * Desc:  bean
 * author: yaoqijun
 * Date: 2016-11-18
 */
public class CoursePackage implements Serializable{

    private Long id;

    // 老师Id
    private Long teacherId;

    // 课程包类型：1.线下包(买线下送线下) 2.线上包(买线下送线上包) ; 3.组合包(买线下送线下,买线上送线上)
    private Integer packageType;

    private Integer isDeleted;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
    public Integer getPackageType() {
        return packageType;
    }

    public void setPackageType(Integer packageType) {
        this.packageType = packageType;
    }
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}