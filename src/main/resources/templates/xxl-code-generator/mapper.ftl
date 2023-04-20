package ${classInfo.prefix}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import ${classInfo.prefix}.${classInfo.appName}.bean.${classInfo.className}Bean;
/**
*  ${classInfo.classComment}
*
*  Created by ${classInfo.author} on '${.now?string('yyyy-MM-dd')}'.
*/
@Repository
public interface ${classInfo.className}Mapper extends BaseMapper<${classInfo.className}Bean> {

}
