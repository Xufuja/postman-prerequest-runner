pm = {
    globals: {
        get: function (key) {
            return Java.type(`dev.xfj.PostmanHandler`).getGlobal(key);
        }
    },
    environment: {
        get: function (key) {
            return Java.type(`dev.xfj.PostmanHandler`).getEnvironment(key);
        },
        values: {
            substitute: function () {
                return "";
            }
        }
    },
    request: {
        headers: {
            add: function (value) {

            }
        },
        body: "",
        method: "",
        url: {
            host: ""
        }
    }
};

request = {
    method: "",
    url: ""
};