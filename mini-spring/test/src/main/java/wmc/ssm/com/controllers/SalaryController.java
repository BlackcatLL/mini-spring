package wmc.ssm.com.controllers;

import wmc.ssm.com.Service.SalaryService;
import wmc.ssm.com.beans.AutoWired;
import wmc.ssm.com.web.mvc.Controller;
import wmc.ssm.com.web.mvc.RequestMapping;
import wmc.ssm.com.web.mvc.RequestParam;

@Controller
public class SalaryController {
    @AutoWired
    private SalaryService salaryService;
    @RequestMapping("/get_salary.json")
    public Integer getSalary(@RequestParam("name") String name, @RequestParam("experience") String experience){
        return salaryService.calSalary(Integer.parseInt(experience));
    }
}
