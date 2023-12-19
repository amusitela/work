package com.cumt.bankapp.service;

import com.cumt.common.MyResult;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author Tony
 * @date 2021-04-03 14:41
 */
public interface IFlowDefinitionService {



    MyResult startProcessInstanceById(String procDefId, Map<String, Object> variables);



}
