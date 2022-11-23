package com.sleepwalker.util;

import com.sleepwalker.vo.ResultVo;

import static com.sleepwalker.util.Constants.ResultFail;
import static com.sleepwalker.util.Constants.ResultSuccess;

/**
 * @package: com.sleepwalker.util
 * @className: ResultVOUtil
 * @author: SleepWalker
 * @description: 结果工具类
 * @date: 20:23
 * @version: 1.0
 */
public class ResultVOUtil {
    /**
     * @param object:
     * @return ResultVo
     * @author SleepWalker
     * @description 成功,code为0
     * @date  20:27
     */
    public static ResultVo success(Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultSuccess);
        resultVo.setData(object);
        return resultVo;
    }

    /**
     * @param :
     * @return ResultVo
     * @author SleepWalker
     * @description 失败, 码为-1
     * @date  20:28
     */
    public static ResultVo fail() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultFail);
        return resultVo;
    }

    /**
     * @param code:
     * @return ResultVo
     * @author SleepWalker
     * @description 失败，自定义码
     */
    public static ResultVo failWithCode(int code) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        return resultVo;
    }
}
