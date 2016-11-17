
import java.util.List;

/**
 * Desc:  Mapper
 * author: yaoqijun
 * Date: 2016-11-18
 */
public interface CoursePackageMapper {

    void create(CoursePackage record);

    void creates(List<CoursePackage> list);

    void delete(Long id);

    CoursePackage findById(Long id);

    List<CoursePackage> findByIds(List<Long> ids);

}
