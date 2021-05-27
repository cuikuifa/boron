package cn.od.moutian.controller;

import cn.od.moutian.core.response.Result;
import cn.od.moutian.core.response.ResultGenerator;
import cn.od.moutian.model.User01;
import cn.od.moutian.service.User01Service;
import cn.od.moutian.util.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 233moutian
 * @date 2018/12/22
 */
@RestController
@RequestMapping("/data/user")
public class User01Controller {
    @Resource
    private User01Service user01Service;

    @PostMapping
    public Result add(@RequestBody User01 user01) throws Exception {
        user01.setId(IdWorker.getFlowIdWorkerInstance().nextId());
        user01.setRegisterTime(new Date());
        user01Service.save(user01);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        user01Service.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody User01 user01) {
        user01Service.update(user01);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        User01 user01 = user01Service.findById(id);
        return ResultGenerator.genOkResult(user01);
    }

    @GetMapping("/count")
    public Result count() {
        int count = user01Service.countAll();
        System.out.println(count);
        return ResultGenerator.genOkResult(count);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User01> list = user01Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
