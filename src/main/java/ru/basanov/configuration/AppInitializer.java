package ru.basanov.configuration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class []{AppConfig.class};
    }
    // Возвращаеют корневую конфигурацию приложения (сервисы и дао-уровень)

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }
    // Возвращает конфигурации сервлета (веб-уровень, который включает в себя контроллеры

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    // Возращает путь, на который мэппитс данный сервлет

    @Override
    protected Filter[] getServletFilters() {
// Создание фильтра кодировки, который позволит работать с русскими символами
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        // Создание фильтра, который добавляет поддержку HTTP-методов (например таких, как PUT), необходимых для REST API
        HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter, httpMethodFilter};
    }
}
