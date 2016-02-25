/*******************************************************************************
 * Copyright (c) 2015, 2016 IBH SYSTEMS GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.packagedrone.utils.converter.impl;

import java.lang.reflect.AnnotatedElement;

import org.eclipse.packagedrone.utils.converter.ConversionContext;
import org.eclipse.packagedrone.utils.converter.Converter;
import org.eclipse.packagedrone.utils.converter.JSON;

import com.google.gson.GsonBuilder;

public class JsonToStringConverter implements Converter
{
    public static final JsonToStringConverter INSTANCE = new JsonToStringConverter ();

    @Override
    public boolean canConvert ( final Class<?> from, final Class<?> to )
    {
        return canConvert ( from, to, null );
    }

    @Override
    public boolean canConvert ( final Class<?> from, final Class<?> to, final AnnotatedElement annotatedElement )
    {
        if ( !to.equals ( String.class ) )
        {
            return true;
        }

        if ( from.isAnnotationPresent ( JSON.class ) )
        {
            return true;
        }

        if ( annotatedElement != null && annotatedElement.isAnnotationPresent ( JSON.class ) )
        {
            return true;
        }

        return false;
    }

    @Override
    public String convertTo ( final Object value, final Class<?> clazz, final ConversionContext context )
    {
        if ( value == null )
        {
            return null;
        }
        return new GsonBuilder ().create ().toJson ( value );
    }
}
