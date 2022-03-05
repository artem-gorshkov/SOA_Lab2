Vue.component(
    'participants',
    {
        template:
            `
                <div>
                    <h1 class="col-xs-1 text-center">Participants</h1>
                    <div>
                        <label for="id">Id:</label>
                        <input id="id" type="number" maxlength="32" v-model="id">
                    </div>
                    <div>
                        <button class="btn btn-danger" v-on:click="add()" type="submit">Add</button>
                    </div>
                    <div>
                        <button class="btn btn-danger" v-on:click="remove()" type="submit">Remove</button>
                    </div>
                </div>
            `,
        data: function() {
            return {
                id: null
            }
        },
        methods: {
            add: function () {
                this.$emit('add', this.id);
            },
            remove: function () {
                this.$emit('remove', this.id);
            },
        }
    }
);
