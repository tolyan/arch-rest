package pro.example.mappers;

import org.apache.ibatis.annotations.Select;
import pro.example.entity.TestPojo;

/**
 * Author: Anatoly Rybalchenko
 * Date: 11/28/13
 * Time: 5:40 PM
 */
public interface TestMapper {
    @Select("SELECT * FROM PERSONS WHERE id = #{id}")
    TestPojo getPerson(int id);
}
