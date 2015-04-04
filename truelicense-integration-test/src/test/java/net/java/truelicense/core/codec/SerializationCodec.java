/*
 * Copyright (C) 2005-2015 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package net.java.truelicense.core.codec;

import net.java.truelicense.core.io.Sink;
import net.java.truelicense.core.io.Source;
import net.java.truelicense.obfuscate.Obfuscate;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;

/**
 * A codec which encodes/decodes an object with an
 * {@link ObjectOutputStream}/{@link ObjectInputStream}.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public class SerializationCodec implements Codec {

    @Obfuscate
    private static final String CONTENT_TYPE = "application/x-java-serialized-object";

    @Obfuscate
    private static final String CONTENT_TRANSFER_ENCODING = "binary";

    /**
     * {@inheritDoc}
     * <p>
     * The implementation in the class {@link SerializationCodec}
     * returns {@code "application/x-java-serialized-object"}.
     *
     * @see java.awt.datatransfer.DataFlavor
     */
    @Override public String contentType() { return CONTENT_TYPE; }

    /**
     * {@inheritDoc}
     * <p>
     * The implementation in the class {@link SerializationCodec}
     * returns {@code "binary"}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc2045">RFC 2045</a>
     */
    @Override public String contentTransferEncoding() {
        return CONTENT_TRANSFER_ENCODING;
    }

    @Override
    public void encode(final Sink sink, final @Nullable Object obj) throws Exception {
        final OutputStream out = sink.output();
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(out);
            try {
                oos.writeObject(obj);
            } finally {
                oos.close();
            }
        } finally {
            out.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public @Nullable <T> T decode(final Source source, final Type expected)
    throws Exception {
        final InputStream in = source.input();
        try {
            final ObjectInputStream oin = new ObjectInputStream(in);
            try { return (T) oin.readObject(); }
            finally { oin.close(); }
        } finally {
            in.close();
        }
    }
}