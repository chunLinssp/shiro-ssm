package org.shiro.maven.common.utli;

import org.shiro.maven.common.constant.Result;

public class ResultApiHandler {

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(0);
        result.setData(object);
        result.setMsg("成功");
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result BuildApiResult(Integer code,String message,Object data){
        Result result = new Result();
        result.setMsg(message);
        result.setCode(code);
        result.setData(data);
        return result;
    }


}
