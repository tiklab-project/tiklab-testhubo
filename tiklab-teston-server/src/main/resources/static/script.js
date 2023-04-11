
var to = {
    header: {},
    query: {},

    setHeader: function(key, value) {
        this.header[key] = value;
        return this;
    },

    setQuery: function(key, value) {
        this.query[key] = value;
        return this;
    },

    getData: function() {
        return {
            header: this.header,
            query: this.query
        };
    },

    getPreUrl:function() {
        return {preUrl:true}
    },



};