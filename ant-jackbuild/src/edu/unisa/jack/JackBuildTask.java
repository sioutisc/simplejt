/* ********************************************************************* *
 *                                                                       *
 *   =============================================================       *
 *   Copyright 2002-2010,                                                *
 *   Christos Sioutis <christos.sioutis@gmail.com>                       *
 *   =============================================================       *
 *   This software was developed during my PhD studies at:               *
 *                                                                       *
 *   Knowledge Based Intelligent Engineering Systems Centre (KES)        *
 *   School of Electrical and Information Engineering                    *
 *   University of South Australia                                       *
 *   =============================================================       *
 *                                                                       *
 *   This file is part of simplejt.                                      *
 *                                                                       *
 *   simplejt is free software: you can redistribute it and/or           *
 *   modify it under the terms of the GNU Lesser General Public License  *
 *   as published by the Free Software Foundation, either version 3 of   *
 *   the License, or (at your option) any later version.                 *
 *                                                                       *
 *   simplejt is distributed in the hope that it will be useful,         *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       *
 *   GNU Lesser General Public License for more details.                 *
 *                                                                       *
 *   You should have received a copy of the GNU Lesser General Public    *
 *   License along with simplejt.                                        *
 *   If not, see <http://www.gnu.org/licenses/>.                         *
 *                                                                       *
 * ********************************************************************* */
 
package edu.unisa.jack;

import org.apache.tools.ant.*;
import org.apache.tools.ant.taskdefs.*;
import org.apache.tools.ant.types.*;
import java.util.*;

/*
 * JackBuildTask.java
 *
 * Created on March 28, 2005, 8:38 PM
 */

/**
 *
 * @author  Christos Sioutis
 */
public class JackBuildTask extends Task {		
    private String help;
    private String version;
    private String all;
    private String recursive;
    private String destDir;
    private String cDestDir;
    private String jDestDir;
    private String gDestDir;
    private String clean;
    private String query;
    private Vector jackType = new Vector();
    private Vector rJackType = new Vector();
    private String jacob;
    private String map;
    private String proj;
    private String projTop;
    private String classPath;
    private String srcDir;
    private Vector property = new Vector();
    private Java javaTask;
    private String showArgs;
    private String include;
 
    public JackBuildTask() {
    }

    public void setHelp(String help) {
        this.help = help;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public void setAll(String all) {
        this.all = all;
    }
    
    public void setRecursive(String recursive) {
        this.recursive = recursive;
    }
    
    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public void setCDestDir(String cDestDir) {
        this.cDestDir = cDestDir;
    }
    
    public void setJDestDir(String jDestDir) {
        this.jDestDir = jDestDir;
    }

    public void setGDestDir(String gDestDir) {
        this.gDestDir = gDestDir;
    }

    public void setClean(String clean) {
        this.clean = clean;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setJackType(String jackType) {
        this.jackType.add(jackType);
    }

    public void setRJackType(String rJackType) {
        this.rJackType.add(rJackType);
    }
    
    public void setJacob(String jacob) {
        this.jacob = jacob;
    }
    
    public void setMap(String map) {
        this.map = map;
    }

    public void setProj(String proj) {
        this.proj = proj;
    }   
    
    public void setProjTop(String projTop) {
        this.projTop = projTop;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }   
    
    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
    }   
    
    public void setProperty(String property) {
        this.property.add(property);
    }
    
    public void add(Task j) {
        javaTask = (Java) j;
    }
    
    public void setShowArgs(String showArgs) {
        this.showArgs = showArgs;
    }
    
    public void setInclude(String include) {
        this.include = include;
    }    
    
    //this should not be required according to Ant documentation
    private String getArguments(){
        StringBuffer sb = new StringBuffer();
        if(help != null && help.equals("true"))
           sb.append(" -h");
        if(version != null && version.equals("true"))
           sb.append(" -v");        
        if(all != null && all.equals("true"))
           sb.append(" -a");
        if(recursive != null && recursive.equals("true"))
           sb.append(" -r");
        if(destDir != null)
           sb.append(" -d "+destDir);
        if(cDestDir != null)
           sb.append(" -dc "+cDestDir);
        if(jDestDir != null)
           sb.append(" -dj "+jDestDir);
        if(gDestDir != null)
           sb.append(" -dg "+gDestDir);
        if(clean != null && clean.equals("true"))
           sb.append(" -c");
        if(query != null && query.equals("true"))
           sb.append(" -q");
        for(int i=0; i<jackType.size(); i++)
           sb.append(" -F"+(String)jackType.get(i));
        for(int i=0; i<rJackType.size(); i++)
           sb.append(" -NF"+(String)rJackType.get(i));
        if(jacob != null && jacob.equals("true"))
           sb.append(" -x");
        if(map != null)
           sb.append(" -map="+map);
        if(proj != null)
           sb.append(" -E"+proj);
        if(projTop != null)
           sb.append(" -P"+projTop);
        if(classPath != null)
           sb.append(" -cp "+classPath);
        if(srcDir != null)
           sb.append(" -wd "+srcDir);
        for(int i=0; i<property.size(); i++)
           sb.append(" -D"+(String)property.get(i));
        if(include != null)
           sb.append(" "+include);	
        return sb.toString();
    }    

    // The method executing the task
    public void execute() throws BuildException {
        if(showArgs!=null && showArgs.equals("true"))
            System.out.println("aos.main.JackBuild"+getArguments());
        javaTask.setClassname("aos.main.JackBuild");
        javaTask.createArg().setLine(getArguments());
	javaTask.setFailonerror(true);
        javaTask.perform();
    }
}
