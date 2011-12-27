package com.incubaid.hudson;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.Label;
import hudson.slaves.Cloud;
import hudson.slaves.NodeProvisioner.PlannedNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.kohsuke.stapler.DataBoundConstructor;

public class SsoCloud extends Cloud {
    private static final Logger LOGGER = Logger.getLogger(SsoCloud.class
            .getName());

    private String server;
    private String path;
    private List<SsoMachine> machines;

    @DataBoundConstructor
    public SsoCloud(String server, String path, List<SsoMachine> machines) {
        super("sso://" + server + path);
        this.server = server;
        this.path = path;
        this.machines = machines;
        LOGGER.info("Initialized Cloud " + this.name);
    }

    public String getServer() {
        return server;
    }

    public String getPath() {
        return path;
    }

    public List<SsoMachine> getMachines() {
        return Collections.unmodifiableList(machines);
    }

    public SsoMachine getMachine(Label label) {
        for (SsoMachine m : this.machines)
            if (m.containsLabel(label))
                return m;
        return null;
    }

    @Override
    public Collection<PlannedNode> provision(Label label, int excessWorkload) {
        LOGGER.info("Provision Cloud " + this.name);
        return new ArrayList<PlannedNode>(excessWorkload);
    }

    @Override
    public boolean canProvision(Label label) {
        return getMachine(label) != null;
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<Cloud> {

        @Override
        public String getDisplayName() {
            return "SSO Cloud";
        }
    }

}
