/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2014 ForgeRock AS.
 */

package org.forgerock.openig.handler.router;

import java.io.IOException;

import org.forgerock.http.protocol.Response;
import org.forgerock.openig.handler.Handler;
import org.forgerock.openig.handler.HandlerException;
import org.forgerock.openig.heap.GenericHeaplet;
import org.forgerock.openig.heap.HeapException;
import org.forgerock.openig.http.Exchange;

@SuppressWarnings("javadoc")
public class StatusHandler implements Handler {
    private final int status;

    public StatusHandler(final int status) {
        this.status = status;
    }

    @Override
    public void handle(final Exchange exchange) throws HandlerException, IOException {
        exchange.response = new Response();
        exchange.response.setStatus(status);
    }

    public static class Heaplet extends GenericHeaplet {

        @Override
        public Object create() throws HeapException {
            return new StatusHandler(config.get("status").required().asInteger());
        }
    }
}
