const path = require("path")

const HtmlWebpackPlugin = require("html-webpack-plugin")

const { VueLoaderPlugin } = require("vue-loader")


module.exports = {
    mode: process.env.NODE_ENV || "development",

    entry: "./src/app",

    output: {
        path: path.resolve(__dirname, "../", "dist"),
        filename: "[name].js",
        publicPath: "./",
    },
    resolve: {
        extensions: ["*", ".js", ".json", ".vue"],
        alias: {
            "vue$": "vue/dist/vue.esm.js"
        },
        fallback: {
            fs: false
        }
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: "vue-loader"
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader"
                },
            },
            {
                test: /\.s?css$/,
                use: [
                    "style-loader",
                    "css-loader",
                    "sass-loader",
                ],
            },
        ]
    },
    plugins: [
        new VueLoaderPlugin(),
        new HtmlWebpackPlugin()
    ]
}