package com.incubaid.hudson;

import java.util.logging.Logger;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.model.Hudson;
import hudson.model.Label;

public class SsoMachine implements Describable<SsoMachine> {
    private static final Logger LOGGER = Logger.getLogger(SsoMachine.class
            .getName());
    private String name;
    private String labelString;

    @DataBoundConstructor
    public SsoMachine(String name, String labelString) {
        this.name = name;
        this.labelString = labelString == null ? "" : labelString;
        LOGGER.info("Creating SSO Machine " + this.name + " with labels "
                + this.labelString);
    }

    public String getName() {
        return name;
    }

    public Object getLabelString() {
        return labelString;
    }

    public boolean containsLabel(Label label) {
        return label == null || Label.parse(this.labelString).contains(label);

    }

    @SuppressWarnings("unchecked")
    public Descriptor<SsoMachine> getDescriptor() {
        return Hudson.getInstance().getDescriptor(getClass());
    }

    @Extension
    public static final class DescriptorImpl extends Descriptor<SsoMachine> {
        @Override
        public String getDisplayName() {
            return "SSO Machine";
        }
    }
}
