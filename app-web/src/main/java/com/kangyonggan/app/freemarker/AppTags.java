package com.kangyonggan.app.freemarker;

import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;

/**
 * @author kangyonggan
 * @since 8/10/18
 */
public class AppTags extends SimpleHash {

    public AppTags() {
        super((ObjectWrapper) null);
        put("user", new UserTag());
        put("guest", new GuestTag());
        put("hasRole", new HasRoleTag());
        put("hasMenu", new HasMenuTag());
    }

}
