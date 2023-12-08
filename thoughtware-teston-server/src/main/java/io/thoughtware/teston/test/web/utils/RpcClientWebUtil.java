package io.thoughtware.teston.test.web.utils;

import io.thoughtware.rpc.client.RpcClient;
import io.thoughtware.rpc.client.config.RpcClientConfig;
import org.springframework.stereotype.Component;

/**
 * rpc
 */
@Component
public class RpcClientWebUtil {

    //rpc配置
   public RpcClient rpcClient(){
        RpcClientConfig rpcClientConfig = RpcClientConfig.instance();
        RpcClient rpcClient = new RpcClient(rpcClientConfig);
        return rpcClient;
    }
}
