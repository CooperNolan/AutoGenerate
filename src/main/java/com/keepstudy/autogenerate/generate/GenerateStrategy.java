package com.keepstudy.autogenerate.generate;

import com.keepstudy.autogenerate.config.GenerateConfig;
import com.keepstudy.autogenerate.domain.TableInfo;
import freemarker.template.Configuration;

public interface GenerateStrategy {

    void createTemplate(GenerateConfig config, TableInfo tableInfo, Configuration cfg);

}
