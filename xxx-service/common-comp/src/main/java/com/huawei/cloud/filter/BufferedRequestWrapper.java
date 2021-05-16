package com.huawei.cloud.filter;

import org.springframework.util.CollectionUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BufferedRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> params = new HashMap<>();

    private ByteArrayInputStream bais = null;

    private BufferedServletInputStream bsis = null;

    private byte[] buffer = null;

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public String getParameter(String name) {
        if (params.get(name) != null && params.get(name).length >= 1) {
            return params.get(name)[0];
        }
        return super.getParameter(name);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(params.keySet());
    }

    @Override
    public String[] getParameterValues(final String name) {
        return params.get(name);
    }

    /**
     * Initializing {@link InputStream#}
     *
     * @param req
     * @throws IOException
     */
    public BufferedRequestWrapper(HttpServletRequest req) throws IOException {

        super(req);

        if (!CollectionUtils.isEmpty(req.getParameterMap())) {
            this.params.putAll(req.getParameterMap());
        }

        // Read InputStream and store its content in a buffer.

        InputStream is = req.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];

        int letti;

        while ((letti = is.read(buf)) > 0) {

            baos.write(buf, 0, letti);

        }

        this.buffer = baos.toByteArray();

    }

    /**
     * This method is used for fetching the inputStream from request.
     *
     * @return
     */
    @Override
    public ServletInputStream getInputStream() {

        this.bais = new ByteArrayInputStream(this.buffer);

        this.bsis = new BufferedServletInputStream(this.bais);

        return this.bsis;

    }


    /**
     * This method is used for fetching the request body.
     *
     * @return
     * @throws IOException
     */
    String getRequestBody() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getInputStream()));

        String line = null;

        StringBuilder inputBuffer = new StringBuilder();

        do {

            line = reader.readLine();

            if (null != line) {

                inputBuffer.append(line.trim());

            }

        }
        while (line != null);

        reader.close();

        return inputBuffer.toString().trim();

    }

    private static final class BufferedServletInputStream extends ServletInputStream {

        private ByteArrayInputStream bais;

        /**
         * Initializing ByteArrayInputStream.
         *
         * @param bais
         */
        public BufferedServletInputStream(ByteArrayInputStream bais) {
            super();
            this.bais = bais;

        }

        /**
         * This method returns the number of remaining bytes that can be read
         * from Inputstream.
         *
         * @return
         */
        @Override
        public int available() {

            return this.bais.available();

        }

        /**
         * This method is used to read the inputStream.
         *
         * @return
         */
        @Override
        public int read() {

            return this.bais.read();

        }

        /**
         * This method is used to read the inputStream.
         *
         * @param buf
         * @param off
         * @param len
         * @return
         */
        @Override
        public int read(byte[] buf, int off, int len) {

            return this.bais.read(buf, off, len);

        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }

}
