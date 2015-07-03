package com.shashi.bhushan.components;

import org.apache.felix.scr.annotations.*;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;

import java.util.Map;

/**
 * Created by shashi on 3/7/15.
 */
@Component(metatype = true, configurationFactory = true)
public class ComponentFactoryExample {
    private String name;
    private String gender;

    /*
     * Name Constant Provides a consistent way to access the Felix's console Author's name property
     * also specifies, the default value of the variable
     */
    @Property(label="Name of the Author",value = "author")
    private static final String AUTHOR_NAME = "author.name";

    /*
     * Gender Constant with which to access the Felix's console Author's Gender property
     * also specified, default value of the variable as well as values for options i.e. DropDown for Felix's console Property
     */
    @Property(
            label = "Author Gender",
            description = "Describe Author Gender",
            options = {
                    @PropertyOption(name = "Male", value = "1. Male"),
                    @PropertyOption(name = "Female", value = "2. Female")
            },
            value = "Female")
    private static final String AUTHOR_GENDER = "author.gender";

    /**
     * @return Author's name
     */
    public String getAuthorName() {
        return this.name;
    }

    /**
     * @return Author's gender
     */
    public String getAuthorGender(){
        return this.gender;
    }

    /**
     * Activate, Modified and Deactivate methods are required when i would want the component to know when it has been activated.
     * - What happens behind the scenes is that SCR calls it's activate method when the component is activated for the first time.
     *
     * - Same thing happens for modified and delete methods as well.
     */
    /**
     * @param context
     * Activated Method to give the initial values to the variables, when bundle is Activated
     */
    @Activate
    protected void activate(@SuppressWarnings("rawtypes") final Map context) {
        // gets Author's Name from context and assign to instance variables
        this.name = PropertiesUtil.toString(context.get(AUTHOR_NAME), "");
        // gets Author's Gender from context and assign to instance variables
        this.gender = PropertiesUtil.toString(context.get(AUTHOR_GENDER),"");
    }

    /*
     * sets variables to null when deactivating bundle
     */
    @Deactivate
    protected void deactivate() {
        this.name = null;
        this.gender = null;
    }

    /**
     * @param context
     * assigns the values to variables when the values are modified in Felix's console
     */
    @Modified
    protected void modified(ComponentContext context){
        // gets values from context and assigns to instance variables
        this.name = PropertiesUtil.toString(
                context.getProperties().get(AUTHOR_NAME), "");
        this.gender = PropertiesUtil.toString(
                context.getProperties().get(AUTHOR_GENDER), "");
    }
}
