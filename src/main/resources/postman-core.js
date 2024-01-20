pm = {
    globals: {
        get: function (key) {
            return Java.type(`dev.xfj.PostmanWrapper`).getGlobal(key);
        }
    },
    environment: {
        get: function (key) {
            return Java.type(`dev.xfj.PostmanWrapper`).getEnvironment(key);
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