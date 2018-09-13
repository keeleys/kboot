package cc.sitec.kboot.controller.course;

import cc.sitec.kboot.common.JsonResult;
import cc.sitec.kboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course/course-info")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public JsonResult list() {
        return JsonResult.success(courseService.selectAllPage(1,10));
    }
}
