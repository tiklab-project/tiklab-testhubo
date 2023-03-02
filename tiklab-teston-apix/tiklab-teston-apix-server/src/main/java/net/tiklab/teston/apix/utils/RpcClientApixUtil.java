package net.tiklab.teston.apix.utils;

import net.tiklab.rpc.client.RpcClient;
import net.tiklab.rpc.client.config.RpcClientConfig;
import org.springframework.stereotype.Component;

/**
 * rpc实例
 */
@Component
public class RpcClientApixUtil {

    //rpc配置
   public RpcClient rpcClient(){
        RpcClientConfig rpcClientConfig = RpcClientConfig.instance();
        RpcClient rpcClient = new RpcClient(rpcClientConfig);
        return rpcClient;
    }
}
