package com.dataart.inquirer.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author Alterovych Ilya
 */
public interface ImageResources extends ClientBundle {

    /**
     * The images consumed by ClientBundle into ImageResources are expected
     * to be in the source tree, not in the external web resources
     * (i.e. in target/ or webapp/);
     */

    @Source("icons/access_denied.jpg")
    ImageResource accessDenied();
    @Source("icons/no-data.png")
    ImageResource noData();
    @Source("icons/under_construction.jpeg")
    ImageResource underConstruction();


    public static ImageResources resources = GWT.create(ImageResources.class);

}