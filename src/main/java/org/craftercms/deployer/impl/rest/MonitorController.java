/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.craftercms.deployer.impl.rest;

import org.craftercms.commons.monitoring.rest.MonitoringRestControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple Rest Controller that monitors Deployer.
 * @author Carlos Ortiz.
 */
@RestController
@RequestMapping(MonitorController.BASE_URL)
public class MonitorController extends MonitoringRestControllerBase {

    /**
     * Base ULR for monitoring services.
     */
    public static final String BASE_URL = "/api/1";

}
