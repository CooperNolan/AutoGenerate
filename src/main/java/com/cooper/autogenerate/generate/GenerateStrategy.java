package com.cooper.autogenerate.generate;

import com.cooper.autogenerate.config.GenerateConfig;
import com.cooper.autogenerate.domain.TableInfo;
import freemarker.template.Configuration;

public interface GenerateStrategy {

    void createTemplate(GenerateConfig config, TableInfo tableInfo, Configuration cfg);

}
