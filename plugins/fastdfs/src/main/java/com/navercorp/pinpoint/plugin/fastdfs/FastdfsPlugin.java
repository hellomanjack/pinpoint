package com.navercorp.pinpoint.plugin.fastdfs;

import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplateAware;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginSetupContext;

/**
 * @program: pinpoint
 * @description:
 * @author: Mr.Wang
 * @create: 2018-04-24 19:07
 **/
public class FastdfsPlugin implements ProfilerPlugin, TransformTemplateAware {

    @Override
    public void setTransformTemplate(TransformTemplate transformTemplate) {

    }

    @Override
    public void setup(ProfilerPluginSetupContext context) {

    }
}
