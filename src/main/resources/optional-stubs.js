CryptoJS = {
    enc: {
        Base64: {
            //Seems that byte[] cannot be used, so performing the Base64 encoding earlier so that stringify() does not need to do anything
            stringify: function (value) {
                return value;
            },
        },
        Utf8: {
            parse: function (value) {
                return Java.type(`dev.xfj.OptionalHandler`).getBase64UTF8Bytes(value);
            }
        }
    },
    HmacSHA256: function (value, key) {
        return Java.type(`dev.xfj.OptionalHandler`).getBase64HMAC256Bytes(value, key);
    },
};

function require(key) {
    return {
        v4: function () {
            return Java.type(`dev.xfj.OptionalHandler`).generateUUID();
        }
    };
}