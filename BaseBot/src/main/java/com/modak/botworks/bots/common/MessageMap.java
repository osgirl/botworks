/*
   Copyright 2018 modakanalytics.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.modak.botworks.bots.common;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.UUID;

/**
 * This class just extends HashMap to provide support for adding some dynamic content to artifacts generated by
 * StringTemplates.
 *
 * @author modakanalytics
 * @version 1.0
 * @since 2017-11-15
 */

public class MessageMap extends HashMap {

    public static final String KEY_UUID = "uuid";
    public static final String KEY_DYNAMIC_UUID = "dynamic_uuid";
    public static final String KEY_CURRENT_TS = "current_ts";
    public static final String KEY_INITIAL_TS = "initial_ts";
    public static final String KEY_TS = "timestamp";

    /**
     * When instantiated, MessageMap seeds a few initial properties related to UUID and Timestamps.
     *
     * <ol><li>uuid - A random UUID that will remain constant if included multiple times in a StringTemplate.</li>
     * <li>dynamic_uuid - A random UUID that will be generated new everytime it is encountered in a StringTempalte.</li>
     * <li>current_ts - A timestamp that will be generated each time encountered in a StringTemplate.</li>
     * <li>initial_ts - A timestamp that will be the same everytime encountered in a StringTempate.</li></ol>
     */
    public MessageMap() {
        put(MessageMap.KEY_UUID, (Object) UUID.randomUUID().toString());
        put(MessageMap.KEY_DYNAMIC_UUID, (Object) UUID.randomUUID().toString());
        put(MessageMap.KEY_CURRENT_TS, new GregorianCalendar().getTimeInMillis());
        put(MessageMap.KEY_INITIAL_TS, new GregorianCalendar().getTimeInMillis());
    }

    /**
     * {@inheritDoc}<br> This will behave exactly like HashMap except when it encounters a request for one of the
     * following strings. See contructor documentation for details on what will be returned.
     *
     * <ol><li>uuid</li><li>dynamic_uuid</li><li>current_ts</li><li>initial_ts</li><li>timestamp</li></ol>
     *
     * @param key the key for the entry in the Map for which value is asked for
     * @return Object   the value of the provided key entry for the Map with the modified behaviour
     */
    @Override
    public java.lang.Object get(java.lang.Object key) {
        if (key.toString().equals(MessageMap.KEY_DYNAMIC_UUID)) {
            put(MessageMap.KEY_DYNAMIC_UUID, (Object) UUID.randomUUID().toString());
        }
        else if (key.toString().equals(KEY_CURRENT_TS)) {
            put(MessageMap.KEY_CURRENT_TS, new GregorianCalendar().getTimeInMillis());
        }
        else if (key.toString().equals(MessageMap.KEY_TS)) {
            return super.get(MessageMap.KEY_INITIAL_TS);
        }
        return (java.lang.Object) super.get(key);
    }
}