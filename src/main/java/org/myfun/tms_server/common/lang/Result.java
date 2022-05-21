package org.myfun.tms_server.common.lang;

import java.io.Serializable;

/**
 * 公共返回方法
 * @author Admin
 */
public class Result implements Serializable {
        private Integer status;
        private String msg;
        private Object data;

        public static Result build() {
            return new Result();
        }

        public static Result ok(String msg) {
            return new Result(200, msg, null);
        }

        public static Result ok(String msg, Object data) {
            return new Result(200, msg, data);
        }

        public static Result error(String msg) {
            return new Result(500, msg, null);
        }

        public static Result error(String msg, Object data) {
            return new Result(500, msg, data);
        }

        private Result() {
        }

        private Result(Integer status, String msg, Object data) {
            this.status = status;
            this.msg = msg;
            this.data = data;
        }

        public Integer getStatus() {
            return status;
        }

        public Result setStatus(Integer status) {
            this.status = status;
            return this;
        }

        public String getMsg() {
            return msg;
        }

        public Result setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Object getObj() {
            return data;
        }

        public Result setObj(Object data) {
            this.data = data;
            return this;
        }

}
