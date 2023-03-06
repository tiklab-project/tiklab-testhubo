package net.tiklab.teston.test.app.utils;

import net.tiklab.rpc.client.RpcClient;
import net.tiklab.rpc.client.config.RpcClientConfig;
import org.springframework.stereotype.Component;

/**
 * rpc
 */
@Component
public class RpcClientAppUtil {

    //rpc配置
   public RpcClient rpcClient(){
        RpcClientConfig rpcClientConfig = RpcClientConfig.instance();
        RpcClient rpcClient = new RpcClient(rpcClientConfig);
        return rpcClient;
    }
}
