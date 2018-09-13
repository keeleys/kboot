package cc.sitec.kboot.model.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "kboot_course_info")
public class CourseInfo {
    @Id
    private Integer id;
    private String name;
}
