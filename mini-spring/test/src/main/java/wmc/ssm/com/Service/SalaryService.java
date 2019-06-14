package wmc.ssm.com.Service;

import wmc.ssm.com.beans.Bean;

@Bean
public class SalaryService {
    public Integer calSalary(Integer experience){
        return experience*5000;
    }

}
