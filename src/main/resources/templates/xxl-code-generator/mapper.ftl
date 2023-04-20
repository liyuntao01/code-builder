package ${classInfo.prefix}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import ${classInfo.prefix}.${classInfo.appName}.entity.${classInfo.className}Bean;
/**
*  ${classInfo.classComment}
*
*   @author  ${classInfo.author} on '
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}'
*/
@Repository
public interface ${classInfo.className}Mapper extends BaseMapper<${classInfo.className}Bean> {

}
