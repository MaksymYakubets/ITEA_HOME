package ua.itea;

import bonus.SexParser;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import tags.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class POMParser extends DefaultHandler {
    private Project project;
    private String currentNode;
    private String parentNode;

    public POMParser() {
        project = new Project();
    }

    @Override
    public void startDocument() {

        System.out.println("Start to parse file 'POM.xml'");
        System.out.println("***************************");
    }

    @Override
    public void endDocument() {
        System.out.println(project.toString());
        System.out.println("***************************");
        System.out.println("'SAX Parcer' successfuly finished to parse file 'POM.xml' ");
        System.out.println("***************************");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("......and starting 'Sex Parser' (Loyalty Bonus!)");
        System.out.println(" ");
        System.out.println(" ");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new SexParser().sexParser();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (currentNode != null) {
            parentNode = currentNode;
        }
        currentNode = qName;
        attributeParcer(qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        currentNode = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = "";
        for (int i = start; i < (start + length); i++) {
            value += ch[i];
        }
        value = value.trim().replaceAll("\n", "");
        if (value.length() > 0) {
            {
                switch (parentNode) {
                    case "project":
                        parseProjectNodes(value);
                        break;

                    case "properties":
                        parsePropsNodes(value);
                        break;

                    case "dependency":
                        dependenciesParcer(value);
                        break;

                    case "plugin":
                        pluginsParcer(value);
                        break;

                    case "descriptorRefs":
                        descriptorRefsParcer(value);
                        break;

                    case "manifest":
                        manifestParcer(value);
                        break;

                    case "execution":
                        executionsParcer(value);
                        break;

                    case "goals":
                        goalsParcer(value);
                        break;
                }
            }
        }
    }

    private void attributeParcer(String qName, Attributes attributes) {
        if ("project".equals(qName)) {
            for (int i = 0; i < attributes.getLength(); i++) {
                String attributesName = attributes.getQName(i);
                switch (attributesName) {
                    case "xmlns":
                        project.setXmlnsAttr(attributes.getValue(i));
                        break;
                    case "xmlns:xsi":
                        project.setXmlnsXsiAttr(attributes.getValue(i));
                        break;
                    case "xsi:schemaLocation":
                        project.setXsiSchemaLocationAttr(attributes.getValue(i));
                        break;
                }
            }
        }
    }

    private void dependenciesParcer(String value) {
        List<Dependency> dependencies = project.getDependencies();
        switch (currentNode) {
            case "groupId":
                dependencies.add(new Dependency());
                dependencies.get(dependencies.size() - 1).setGroupID(value);
                break;
            case "artifactId":
                dependencies.get(dependencies.size() - 1).setArtifactID(value);
                break;
            case "version":
                dependencies.get(dependencies.size() - 1).setVersion(value);
                break;
            case "scope":
                dependencies.get(dependencies.size() - 1).setScope(value);
                break;
        }
    }

    private void pluginsParcer(String value) {
        List<Plugin> plugins = project.getBuild().getPlugins();
        if (plugins.isEmpty()) {
            plugins.add(new Plugin());
        }
        Plugin plugin = plugins.get(plugins.size() - 1);
        switch (currentNode) {
            case "artifactId":
                plugin.setArtifactID(value);
                break;
            case "version":
                plugin.setVersion(value);
                break;
        }
    }

    private void descriptorRefsParcer(String value) {
        List<Plugin> plugins = project.getBuild().getPlugins();
        Plugin plugin = plugins.get(plugins.size() - 1);
        Configuration configuration = plugin.getConfiguration();
        DescriptorRefs descriptorRefs = configuration.getDescriptorRefs();
        switch (currentNode) {
            case "descriptorRef":
                descriptorRefs.setDescriptorRef(value);
                break;
        }
    }

    private void manifestParcer(String value) {
        List<Plugin> plugins = project.getBuild().getPlugins();
        Plugin plugin = plugins.get(plugins.size() - 1);
        Configuration configuration = plugin.getConfiguration();
        Archive archive = configuration.getArchive();
        Manifest manifest = archive.getManifest();
        switch (currentNode) {
            case "mainClass":
                manifest.setMainClass(value);
                break;
        }
    }

    private void executionsParcer(String value) {
        List<Plugin> plugins = project.getBuild().getPlugins();
        Plugin plugin = plugins.get(plugins.size() - 1);
        List<Execution> executions = plugin.getExecutions();
        switch (currentNode) {
            case "id":
                Execution execution = new Execution();
                executions.add(execution);
                execution.setID(value);
                break;
            case "phase":
                execution = executions.get(executions.size() - 1);
                execution.setPhase(value);
                break;
        }
    }

    private void goalsParcer(String value) {
        List<Plugin> plugins = project.getBuild().getPlugins();
        Plugin plugin = plugins.get(plugins.size() - 1);
        List<Execution> executions = plugin.getExecutions();
        Execution execution = executions.get(executions.size() - 1);
        Goals goals = execution.getGoals();

        switch (currentNode) {
            case "goal": {
                goals.setGoal(value);
                break;
            }
        }
    }

    private void parsePropsNodes(String value) {
        Properties properties = project.getProperties();
        switch (currentNode) {
            case "project.build.sourceEncoding":
                properties.setProjectBuildSourceEncoding(value);
                break;
            case "maven.compiler.source":
                properties.setMavenCompilerSource(value);
                break;
            case "maven.compiler.target":
                properties.setMavenCompilerTarget(value);
                break;
        }
    }

    private void parseProjectNodes(String value) {
        switch (currentNode) {
            case "modelVersion":
                project.setModelVersion(value);
                break;
            case "groupId":
                project.setGroupID(value);
                break;
            case "artifactId":
                project.setArtifactID(value);
                break;
            case "version":
                project.setVersion(value);
                break;
            case "name":
                project.setName(value);
                break;
            case "url":
                project.setURL(value);
                break;
        }
    }
}