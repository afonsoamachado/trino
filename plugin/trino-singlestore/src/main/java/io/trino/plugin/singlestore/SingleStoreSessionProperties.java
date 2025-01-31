/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.singlestore;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import io.trino.plugin.base.session.SessionPropertiesProvider;
import io.trino.spi.connector.ConnectorSession;
import io.trino.spi.session.PropertyMetadata;

import java.util.List;

import static io.trino.spi.session.PropertyMetadata.booleanProperty;

public final class SingleStoreSessionProperties
        implements SessionPropertiesProvider
{

    private static final String CASE_INSENSITIVE_PREDICATE_CHARACTER_PUSHDOWN = "case_insensitive_predicate_character_pushdown";

    private final List<PropertyMetadata<?>> sessionProperties;

    @Inject
    public SingleStoreSessionProperties(SingleStoreConfig config)
    {
        this.sessionProperties = ImmutableList.<PropertyMetadata<?>>builder()
                .add(booleanProperty(
                        CASE_INSENSITIVE_PREDICATE_CHARACTER_PUSHDOWN,
                        "Force character predicative pushdown.",
                        config.isCaseInsensitivePredicateCharacterPushdown(),
                        false))
                .build();
    }

    public static boolean getCaseInsensitivePredicateCharacterPushdown(ConnectorSession session)
    {
        return session.getProperty(CASE_INSENSITIVE_PREDICATE_CHARACTER_PUSHDOWN, Boolean.class);
    }

    @Override
    public List<PropertyMetadata<?>> getSessionProperties()
    {
        return sessionProperties;
    }
}
