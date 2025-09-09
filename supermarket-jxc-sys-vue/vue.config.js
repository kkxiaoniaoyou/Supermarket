module.exports = {
    devServer: {
        proxy: {
            '/api': {
                // target: 'http://123.249.43.16:3000',
                target: 'http://localhost:8099',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}
