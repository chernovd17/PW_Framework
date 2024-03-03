package LOGGER.withlog4j2;

import management.playwright.run_management.Sessions;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.LogEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(name = "CustomUIAppender", category = "Core", elementType = "appender", printObject = true)
public class CustomUIAppender extends AbstractAppender {

    protected CustomUIAppender(String name, Filter filter, Layout<? extends Serializable> layout,
                               boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    @PluginFactory
    public static CustomUIAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Filter") Filter filter,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginAttribute(value = "ignoreExceptions", defaultBoolean = true) boolean ignoreExceptions) {
        // Create and return an instance of CustomUIAppender
        return new CustomUIAppender(name, filter, layout, ignoreExceptions);
    }

    @Override
    public synchronized void append(LogEvent event) {
        Sessions.getCurrentSession().getLoggerSession().addRow(event);
        //TestLogger.getTestLogger().addRow(event);
    }

}